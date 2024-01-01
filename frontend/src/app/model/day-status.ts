export interface DayStatus {
  dayOfWeek: string;
	date: string;
	minStatusId: number;// 800: clear sky
	maxStatusId: number;// 804: overcast clouds
	minTemp: number;	// 15 C
	maxTemp: number;	// 25 C
	windSpeed: number;// 7.4 km/h
}
