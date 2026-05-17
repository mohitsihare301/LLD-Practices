package splitwise.strategy;

import java.util.Map;
import splitwise.enums.SplitType;

public class SplitStrategyFactory {
    
    public SplitStrategy getSplitStrategy(SplitType splitType, Object config){
        switch (splitType) {
            case SplitType.EQUAL:
                return new EqualSplit();
            case SplitType.EXACT:
                return new ExactSplit((Map<String,Double>)config);
            case SplitType.PERCENTAGE:
                return new PercentageSplit((Map<String,Double>)config);
            default:
                throw new IllegalArgumentException("Invalid Split Type");
        }
    }
}
