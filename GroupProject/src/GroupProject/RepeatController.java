
package GroupProject;

public class RepeatController {
	
		private boolean isRepeated;
		private boolean[] isRepeatedDaily; // index 0 = Sunday, index 1 = Monday, ...
		private StringBuilder daysRepeated; // Sunday Monday Tuesday Wednesday ...
		private boolean isRepeatedWeekly;
		private boolean isRepeatedMonthly;
		private boolean isRepeatedYearly;
		
		public RepeatController() {
			
			isRepeated = false;
			isRepeatedDaily = new boolean[7];
			
			//set Sun-Mon false
			for(int i = 0; i < 7; i++){
				isRepeatedDaily[i] = false;
			}
			
			daysRepeated = new StringBuilder(70);
			
			isRepeatedWeekly = false;
			isRepeatedMonthly = false;
			isRepeatedYearly = false;
		}
		
		private void setIsRepeated(){
			/* set isRepeated true if an event is repeated on any day 
			 * and set false if event is repeated on no days 
			 */
			this.isRepeated = false;
			for(int i = 0; i < 7; i++){
				
				if(this.isRepeatedDaily[i]){
					this.isRepeated = true;
					break;
				}
			}
			// if event is repeated yearly, monthly, or weekly: isRepeated = true
			if(isRepeatedWeekly || isRepeatedMonthly || isRepeatedYearly){
				isRepeated = true;
			}
		}

		public boolean getIsRepeated() {
			return isRepeated;
		}
		
		public boolean[] getIsRepeatedDaily() {
			return isRepeatedDaily;
		}
		
		// given an index, set isRepeated for that day equal to true or false 
		public void setIsRepeatedDaily(int dayIndex, boolean isRepeatedDaily) {
			
			this.isRepeatedDaily[dayIndex] = isRepeatedDaily;
			
			setIsRepeated();
			setDaysRepeated();
		}
		
		public boolean getIsRepeatedWeekly() {
			return isRepeatedWeekly;
		}
		
		public void setIsRepeatedWeekly(boolean isRepeatedWeekly) {
			this.isRepeatedWeekly = isRepeatedWeekly;
			setIsRepeated();
		}
		
		public boolean getIsRepeatedMonthly() {
			return isRepeatedMonthly;
		}
		
		public void setIsRepeatedMonthly(boolean isRepeatedMonthly) {
			this.isRepeatedMonthly = isRepeatedMonthly;
			setIsRepeated();
		}
		
		public boolean getIsRepeatedYearly() {
			return isRepeatedYearly;
		}
		
		public void setIsRepeatedYearly(boolean isRepeatedYearly) {
			this.isRepeatedYearly = isRepeatedYearly;
			setIsRepeated();
		}
		
		private void setDaysRepeated(){
			
			/* create a new StringBuilder */ 
			this.daysRepeated = new StringBuilder(70);
			
			/* and add the days the event is repeated to the array */
			for(int i = 0; i < 7; i++){
				if(this.isRepeatedDaily[i]){
					this.daysRepeated.append(DateBook.DAYS_OF_THE_WEEK[i]);
					this.daysRepeated.append(" ");
				}
			}
		}
		public StringBuilder getDaysRepeated(){
			return daysRepeated;
		}

		@Override
		public String toString() {
			return "RepeatController [isRepeated=" + isRepeated 
					+ ", daysRepeated=" + daysRepeated + ", isRepeatedWeekly=" + isRepeatedWeekly
					+ ", isRepeatedMonthly=" + isRepeatedMonthly + ", isRepeatedYearly=" + isRepeatedYearly + "]";
		}
}
