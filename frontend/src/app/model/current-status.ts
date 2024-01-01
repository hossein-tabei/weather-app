import { AirQualityIndex } from "./air-quality-index";

export interface CurrentStatus {
  city: string;		      // Oakland
	country: string;		  // US
	statusId: number;     // 800: Clear
	temperature: number;  // 19 C
	minTemp: number;		  // 15 C
	maxTemp: number;      // 25 C
	humidity: number;		  // 35 %
	realFeel: number;		  // 14 C
	chanceOfRain: number;	// 10 %
}
