package edu.udel.cis.vsl.abc.analysis.dataflow.common;

import edu.udel.cis.vsl.abc.analysis.dataflow.IF.AbstractValue;
import edu.udel.cis.vsl.abc.util.IF.Pair;
import edu.udel.cis.vsl.sarl.IF.number.IntegerNumber;
import edu.udel.cis.vsl.sarl.IF.number.Interval;
import edu.udel.cis.vsl.sarl.IF.number.NumberFactory;
import edu.udel.cis.vsl.sarl.number.IF.Numbers;

/**
 * This class is a specific implementation of the abstract value named Interval.
 * 
 * It contains operations used in interval;
 * 
 * The actual calculations of various operations are from SARL;
 *          
 * @author dxu
 */

public class IntervalValue extends AbstractValue {

	private static NumberFactory numFactory = Numbers.REAL_FACTORY;
	Interval interval;

	public IntervalValue(){
		this.interval = numFactory.emptyIntegerInterval();
	}

	public IntervalValue(Interval interval){
		this.interval = interval;
	}
	
	public boolean isEmpty(){
		return this.interval.isEmpty();
	}
	
	public Interval getInterval(){
		return this.interval;
	}

	@Override
	public AbstractValue plus(AbstractValue leftValue,
			AbstractValue rightValue) {

		IntervalValue lv = (IntervalValue) leftValue;
		IntervalValue rv = (IntervalValue) rightValue;
		IntervalValue res = new IntervalValue();

		res.interval = numFactory.add(lv.interval, rv.interval);

		return res;
	}

	@Override
	public AbstractValue minus(AbstractValue leftValue, AbstractValue rightValue) {

		IntervalValue lv = (IntervalValue) leftValue;
		IntervalValue rv = (IntervalValue) rightValue;

		if(lv.interval.isIntegral() && rv.interval.isIntegral()){

			IntegerNumber lLower = (IntegerNumber) lv.interval.lower();
			IntegerNumber lUpper = (IntegerNumber) lv.interval.upper();

			IntegerNumber rLower = (IntegerNumber) rv.interval.lower();
			IntegerNumber rUpper = (IntegerNumber) rv.interval.upper();


			IntegerNumber lower = numFactory.subtract(lLower, rUpper);
			IntegerNumber upper = numFactory.subtract(lUpper, rLower);


			Interval returnInterval = numFactory.newInterval(true, lower, false, upper, false);

			IntervalValue result = new IntervalValue(returnInterval);

			return result;
		}
		else{
			assert false: "Unsupported";
		return null;
		}
	}

	@Override
	public AbstractValue multiply(AbstractValue leftValue,
			AbstractValue rightValue) {
		IntervalValue lv = (IntervalValue) leftValue;
		IntervalValue rv = (IntervalValue) rightValue;
		IntervalValue res = new IntervalValue();

		res.interval = numFactory.multiply(lv.interval, rv.interval);

		return res;
	}

	@Override
	public AbstractValue divide(AbstractValue leftValue, AbstractValue rightValue) {
		IntervalValue lv = (IntervalValue) leftValue;
		IntervalValue rv = (IntervalValue) rightValue;
		IntervalValue res = new IntervalValue();

		res.interval = numFactory.divide(lv.interval, rv.interval);

		return res;
	}

	@Override
	public AbstractValue setValue(long value) {
		IntegerNumber intNum = numFactory.integer(value);
//		Interval a = numFactory.newInterval(true, intNum, false, intNum, false);
		IntervalValue res = new IntervalValue(numFactory.newInterval(true, intNum, false, intNum, false));
//		res.interval = a;
		return res;
	}

	@Override
	public AbstractValue top() {
		return new IntervalValue(numFactory.universalIntegerInterval());
	}
	
	public boolean isTop(){
		return this.equals(numFactory.universalIntegerInterval());
	}

	@Override
	public AbstractValue union(AbstractValue leftValue, AbstractValue rightValue){
		IntervalValue lv = (IntervalValue) leftValue;
		IntervalValue rv = (IntervalValue) rightValue;
		IntervalValue res = new IntervalValue(numFactory.join(lv.interval, rv.interval));

		return res;
	}

 	@Override
	public boolean equals(Object obj) {
		if(obj instanceof IntervalValue){
			return this.interval.equals(((IntervalValue) obj).interval);
		}
		return false;
	}

	@Override
	public int hashCode(){
		Pair<Integer, Integer> co = new Pair<Integer,Integer>(null, null);
		co.left = this.interval.lower().hashCode();
		co.right = this.interval.lower().hashCode();
		return co.hashCode();
	}
	
	@Override
	public String toString(){
		return this.interval.toString();
	}

}
