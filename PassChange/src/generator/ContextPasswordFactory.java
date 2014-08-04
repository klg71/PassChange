package generator;

public class ContextPasswordFactory {
	public ContextPasswordGenerator construct(Context context){
		switch(context){
		case HISTORY: return new HistoryContextGenerator();
		case LANGUAGE: return new LanguageContextGenerator();
		case MATH: return new MathContextGenerator();
		case PROGRAMMING: return new ProgrammingContextGenerator();
		}
		return null;
	}
}
