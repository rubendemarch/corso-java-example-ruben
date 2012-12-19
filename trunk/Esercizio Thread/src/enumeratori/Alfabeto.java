package enumeratori;

public enum Alfabeto {

	A("A"),B("B"),C("C"),D("D"),E("E"),F("F"),G("G"),H("H"),I("I"),L("L"),
	M("M"),N("N"),O("O"),P("P"),Q("Q"),R("R"),S("S"),T("T"),U("U"),V("V"),
	Z("Z");
	
	/*a("a"),b("b"),c("c"),d("d"),f("f"),g("g"),h("h"),i("i"),l("l"),m("m"),
	n("n"),o("o"),p("p"),q("q"),r("r"),s("s"),t("t"),u("u"),v("v"),z("z");*/
	
	private String value;
	
	private Alfabeto(String val)
	{
		value = val;
	}

}
