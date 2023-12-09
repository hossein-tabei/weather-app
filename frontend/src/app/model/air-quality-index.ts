import { PolutionIndex } from "./polution-index";

export interface AirQualityIndex {
  numericlevel: number;		// 28
	verbalLevel: string;		// Good
	description: string;		// Air quality is good. A perfect day for a walk!
	publicationInfo: string;// Tehran Published at 07:30
	polutionIndexes: PolutionIndex[];
}
