package it.ecommerce.servlet.common.brands;

import it.ecommerce.servlet.RootServlet;
import it.ecommerce.util.FileNameGenerator;
import it.ecommerce.util.KeyGenerator;
import it.ecommerce.util.constants.Common;
import it.ecommerce.util.constants.Request;
import it.ecommerce.util.log.MyLogger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

/**
 * Servlet implementation class ManageBrand
 */
public class ManageBrand extends RootServlet {
	private static final long serialVersionUID = 1L;
	private MyLogger log;
	private String imagePath="images\\brands\\";
	private String imageUrl="/images/brands/";
	/**
	 * @see RootServlet#RootServlet()
	 */
	public ManageBrand() {
		super();
		log = new MyLogger(this.getClass());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="doGet";
		log.start(metodo);
		process(request, response);
		log.end(metodo);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="doPost";
		log.start(metodo);
		process(request, response);
		log.end(metodo);
	}

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final String metodo="process";
		log.start(metodo);
		initProcess(request);
		ResourceBundle rb = (ResourceBundle) request.getAttribute(Request.ResourceBundle);
		if (StringUtils.isEmpty(commonAction)) {
			String msg=null;
			try {
				request.getParts();
			} catch (IllegalStateException e) {
				log.error(metodo, "IllegalStateException", e);
				msg="file.size.limit.exceeded";
			} catch (ServletException e) {
				log.error(metodo, "ServletException", e);
				msg="servlet.exception";
			} catch (IOException e) {
				log.error(metodo, "IOException", e);
				msg="io.exception";
			} finally{
				request.setAttribute(Common.ACTION, Common.ADD);
				request.setAttribute("msg", rb.getString(msg));
			}
		}
		if(Common.SAVE.equals(request.getParameter(Common.CUSTOM_ACTION))){
			HashMap<String, Object> brand = new HashMap<String, Object>();
			brand.put("colName","NAME");
			brand.put("tableName","BRANDS");
			brand.put("colValue", request.getParameter("name"));
			if(commonAction.equals(Common.MODIFY)){
				brand.put("idColName", "ID_BRAND");
				brand.put("idColValue", request.getParameter("idColumnValue"));
			}
			SqlSession sql = sqlSessionFactory.openSession();
			int count=0;
			try {
				count = sql.selectOne("Common.count", brand);
			} catch (Exception e) {
				log.error(metodo, request.getSession().getId(), e);
			} finally{
				sql.close();
			}
			if(count>0){request.setAttribute(
					"msg",
					rb.getString("salvataggio.alreadyInserted"));
			}else{
				
				String logoUrl=request.getParameter("logoUrl");
				if("image".equals(request.getParameter("radioLogoUrl"))){
				//upload image
					Part filePart = request.getPart("logoImg");//scarica il file
				/*	String realPath=request.getServletContext().getRealPath("/");
					String contextPath=request.getRealPath("/");
					String realPathS=request.getServletContext().getContextPath();
					String contextPathS=request.getContextPath();
					System.out.println(realPath);
					System.out.println(contextPath);
					System.out.println(realPathS);
					System.out.println(contextPathS);
					
					filePart.write(contextPathS+"image\\brands\\");
					*/
					if(filePart!=null && filePart.getSize()>0){//così se il primo è vero va a vedere il secondo termine, se scambiati metterebbe un nullpointer exception
						String ext = request.getParameter("ext");
						ext=ext.substring(ext.lastIndexOf('.'));
						String fileNameGen=FileNameGenerator.fileNameGen(ext);
						filePart.write(realPath + imagePath + fileNameGen);//scrive il file sul server
						logoUrl = urlSite +contextPath + imageUrl + fileNameGen;//crea url nella parameter da passare come si farebbe altrimenti
					}
				}
				boolean success=insertOrUpdateBrand(request,logoUrl);
				request.setAttribute(
					"msg",
					rb.getString((success)?"salvataggio.ok":"salvataggio.ko"));
				String oldLogoUrl = request.getParameter("oldLogoUrl");
				if(success
					&& Common.MODIFY.equals(commonAction)
					&& !StringUtils.isEmpty(oldLogoUrl)
					&& oldLogoUrl.contains(urlSite)
					&& oldLogoUrl.contains("/")
					&& logoUrl.contains("/")
					&& !oldLogoUrl.substring(oldLogoUrl.lastIndexOf('/'))
						.equals(logoUrl.substring(logoUrl.lastIndexOf('/')))
					){
					//cancella la vecchia immagine, però solo se è stato fatto il commit
					try{
						log.info(metodo, "try delete");
						new File(realPath+imagePath+oldLogoUrl.substring(oldLogoUrl.lastIndexOf('/'))).delete();//avrà i diritti per scrivere? ma tanto prima ha scritto
					}catch(Exception e){
						log.error(metodo, "on delete file", e);
					}
				}
			}
		}
		if(Common.LIST.equals(commonAction)){
			
			SqlSession sql= sqlSessionFactory.openSession();
			request.setAttribute(
					"brandList",
					sql.selectList("Brand.list"));
			sql.close();
		/*	request
			.getRequestDispatcher("jsp/manage/brands/brandList.jsp")
				.forward(request, response);*/
		}
/*		if(!"LIST".equals(action)){
			request
				.getRequestDispatcher("jsp/manage/brands/insertBrand.jsp")
					.forward(request, response);
		}*/
		if(Common.DETAIL.equals(commonAction)||Common.MODIFY.equals(commonAction)){
			SqlSession sql= sqlSessionFactory.openSession();
			HashMap<String, Object> brand = new HashMap<String, Object>();
			brand.put("colName","ID_BRAND");
			brand.put("tableName","BRANDS");
			brand.put("colValue", request.getParameter("commonId"));
			request.setAttribute("brand",sql.selectOne("Common.detail", brand));
			sql.close();
		}
		if(Common.ADD.equals(commonAction)){
			request.setAttribute("brand",new HashMap<String, Object>() );
		}
		dispatch(request, response);
		log.end(metodo);
	}

	private synchronized boolean insertOrUpdateBrand(
			HttpServletRequest request,
			String logoUrl){
		final String metodo="insertOrUpdateBrand";
		log.start(metodo);
		
		SqlSession sql = sqlSessionFactory.openSession(TransactionIsolationLevel.READ_COMMITTED);
		int rowsAffected =0;
		try {
			HashMap<String, Object> brand = new HashMap<String, Object>();
			brand.put(
				"ID_BRAND",
				Common.MODIFY.equals(commonAction)?
					request.getParameter("idColumnValue"):
						KeyGenerator.keyGen(sql, "ID_BRAND", "brands", "B"));
			brand.put("IS_VISIBLE", request.getParameter("isVisible"));
			brand.put("URL", request.getParameter("url"));
			brand.put("LOGO_URL", logoUrl);
			brand.put("NAME", request.getParameter("name"));
			brand.put("IS_DELETED", request.getParameter("isDeleted"));
			rowsAffected = Common.ADD.equals(commonAction)?
					sql.insert("Brand.add", brand):
						sql.update("Brand.update", brand);
			
			sql.commit();
		} catch (Exception e) {
			log.error(metodo, "Sessione: "+request.getSession().getId(), e);
			sql.rollback();
		} finally{
			sql.close();
			log.end(metodo);
		}
		return rowsAffected>0;
	}
}
