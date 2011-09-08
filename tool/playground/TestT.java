import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public class TestT {
	public static void main(String[] args) throws Exception {
		TLexer t = new TLexer(new ANTLRFileStream(args[0]));
		CommonTokenStream tokens = new CommonTokenStream(t);
//		tokens.fill();
//		for (Object tok : tokens.getTokens()) {
//			System.out.println(tok);
//		}
		TParser p = new TParser(tokens);
		p.setBuildParseTrees(true);
		TParser.sContext tree = p.s();
		System.out.println(tree.toStringTree(p));

		ParseTreeVisitor visitor = new ParseTreeVisitor();
		TListener listener = new BlankTListener() {
			public void enterEveryRule(ParserRuleContext ctx) {
				System.out.println("enter rule "+TParser.ruleNames[ctx.ruleIndex]);
			}
			public void exitRule(TParser.sContext ctx) { // specific to rule s
				System.out.println("exit rule s");
			}
		};
		visitor.visit(listener, tree);
	}
}
