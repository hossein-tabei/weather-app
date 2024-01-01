export class WeatherIcon {

  static readonly CLEAR_DAY = new WeatherIcon('01d', 'weather-icon clear_sky');
  static readonly CLEAR_NIGHT = new WeatherIcon('01n', 'weather-icon clear_sky night');

  static readonly FEW_CLOUDS_DAY = new WeatherIcon('02d', 'weather-icon few_clouds');
  static readonly FEW_CLOUDS_NIGHT = new WeatherIcon('02n', 'weather-icon few_clouds night');

  static readonly SCATTERED_CLOUDS_DAY = new WeatherIcon('03d', 'weather-icon scattered_clouds');
  static readonly SCATTERED_CLOUDS_NIGHT = new WeatherIcon('03n', 'weather-icon scattered_clouds night');

  static readonly BROKEN_CLOUDS_DAY = new WeatherIcon('04d', 'weather-icon broken_clouds');
  static readonly BROKEN_CLOUDS_NIGHT = new WeatherIcon('04n', 'weather-icon broken_clouds night');

  static readonly SHOWER_RAIN_DAY = new WeatherIcon('09d', 'weather-icon shower_rain');
  static readonly SHOWER_RAIN_NIGHT = new WeatherIcon('09n', 'weather-icon shower_rain night');

  static readonly RAIN_DAY = new WeatherIcon('10d', 'weather-icon rain');
  static readonly RAIN_NIGHT = new WeatherIcon('10n', 'weather-icon rain night');

  static readonly THUNDERSTORM_DAY = new WeatherIcon('11d', 'weather-icon thunderstorm');
  static readonly THUNDERSTORM_NIGHT = new WeatherIcon('11n', 'weather-icon thunderstorm night');

  static readonly SNOW_DAY = new WeatherIcon('13d', 'weather-icon snow');
  static readonly SNOW_NIGHT = new WeatherIcon('13n', 'weather-icon snow night');

  static readonly MIST_DAY = new WeatherIcon('50d', 'weather-icon mist');
  static readonly MIST_NIGHT = new WeatherIcon('50n', 'weather-icon mist night');

  private static readonly MAP_BY_SYMBOL = new Map<string, WeatherIcon>();
  static {
    for (let [key, value] of Object.entries(WeatherIcon)) {
      if (key!=='MAP_BY_SYMBOL') {
        this.MAP_BY_SYMBOL.set(value.symbol, value);
      }
    }
  }

  private constructor(private symbol: string, private iconClassName: string){}

  public static getInstanceBySymbol(symbol: string): WeatherIcon | undefined {
    return this.MAP_BY_SYMBOL.get(symbol);
  }

  public getSymbol(): string {
    return this.symbol;
  }

  public getIconClassName(): string {
    return this.iconClassName;
  }
}

export class WeatherStatusEnum {

  // Group 2xx: Thunderstorm
  static readonly _200 = new WeatherStatusEnum(200, 'thunderstorm with light rain', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _201 = new WeatherStatusEnum(201, 'thunderstorm with rain', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _202 = new WeatherStatusEnum(202, 'thunderstorm with heavy rain', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _210 = new WeatherStatusEnum(210, 'light thunderstorm', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _211 = new WeatherStatusEnum(211, 'thunderstorm', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _212 = new WeatherStatusEnum(212, 'heavy thunderstorm', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _221 = new WeatherStatusEnum(221, 'ragged thunderstorm', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _230 = new WeatherStatusEnum(230, 'thunderstorm with light drizzle', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _231 = new WeatherStatusEnum(231, 'thunderstorm with drizzle', WeatherIcon.THUNDERSTORM_DAY);
  static readonly _232 = new WeatherStatusEnum(232, 'thunderstorm with heavy drizzle', WeatherIcon.THUNDERSTORM_DAY);

  // Group 3xx: Drizzle
  static readonly _300 = new WeatherStatusEnum(300, 'light intensity drizzle', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _301 = new WeatherStatusEnum(301, 'drizzle', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _302 = new WeatherStatusEnum(302, 'heavy intensity drizzle', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _310 = new WeatherStatusEnum(310, 'light intensity drizzle rain', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _311 = new WeatherStatusEnum(311, 'drizzle rain', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _312 = new WeatherStatusEnum(312, 'heavy intensity drizzle rain', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _313 = new WeatherStatusEnum(313, 'shower rain and drizzle', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _314 = new WeatherStatusEnum(314, 'heavy shower rain and drizzle', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _321 = new WeatherStatusEnum(321, 'shower drizzle', WeatherIcon.SHOWER_RAIN_DAY);

  // Group 5xx: Rain
  static readonly _500 = new WeatherStatusEnum(500, 'light rain', WeatherIcon.RAIN_DAY);
  static readonly _501 = new WeatherStatusEnum(501, 'moderate rain', WeatherIcon.RAIN_DAY);
  static readonly _502 = new WeatherStatusEnum(502, 'heavy intensity rain', WeatherIcon.RAIN_DAY);
  static readonly _503 = new WeatherStatusEnum(503, 'very heavy rain', WeatherIcon.RAIN_DAY);
  static readonly _504 = new WeatherStatusEnum(504, 'extreme rain', WeatherIcon.RAIN_DAY);
  static readonly _511 = new WeatherStatusEnum(511, 'freezing rain', WeatherIcon.SNOW_DAY);
  static readonly _520 = new WeatherStatusEnum(520, 'light intensity shower rain', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _521 = new WeatherStatusEnum(521, 'shower rain', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _522 = new WeatherStatusEnum(522, 'heavy intensity shower rain', WeatherIcon.SHOWER_RAIN_DAY);
  static readonly _531 = new WeatherStatusEnum(531, 'ragged shower rain', WeatherIcon.SHOWER_RAIN_DAY);

  // Group 6xx: Snow
  static readonly _600 = new WeatherStatusEnum(600, 'light snow', WeatherIcon.SNOW_DAY);
  static readonly _601 = new WeatherStatusEnum(601, 'snow', WeatherIcon.SNOW_DAY);
  static readonly _602 = new WeatherStatusEnum(602, 'heavy snow', WeatherIcon.SNOW_DAY);
  static readonly _611 = new WeatherStatusEnum(611, 'sleet', WeatherIcon.SNOW_DAY);
  static readonly _612 = new WeatherStatusEnum(612, 'light shower sleet', WeatherIcon.SNOW_DAY);
  static readonly _613 = new WeatherStatusEnum(613, 'shower sleet', WeatherIcon.SNOW_DAY);
  static readonly _615 = new WeatherStatusEnum(615, 'light rain and snow', WeatherIcon.SNOW_DAY);
  static readonly _616 = new WeatherStatusEnum(616, 'rain and snow', WeatherIcon.SNOW_DAY);
  static readonly _620 = new WeatherStatusEnum(620, 'light shower snow', WeatherIcon.SNOW_DAY);
  static readonly _621 = new WeatherStatusEnum(621, 'shower snow', WeatherIcon.SNOW_DAY);
  static readonly _622 = new WeatherStatusEnum(622, 'heavy shower snow', WeatherIcon.SNOW_DAY);

  // Group 7xx: Atmosphere
  static readonly _701 = new WeatherStatusEnum(701, 'mist', WeatherIcon.MIST_DAY);
  static readonly _711 = new WeatherStatusEnum(711, 'smoke', WeatherIcon.MIST_DAY);
  static readonly _721 = new WeatherStatusEnum(721, 'haze', WeatherIcon.MIST_DAY);
  static readonly _731 = new WeatherStatusEnum(731, 'sand/dust whirls', WeatherIcon.MIST_DAY);
  static readonly _741 = new WeatherStatusEnum(741, 'fog', WeatherIcon.MIST_DAY);
  static readonly _751 = new WeatherStatusEnum(751, 'sand', WeatherIcon.MIST_DAY);
  static readonly _761 = new WeatherStatusEnum(761, 'dust', WeatherIcon.MIST_DAY);
  static readonly _762 = new WeatherStatusEnum(762, 'volcanic ash', WeatherIcon.MIST_DAY);
  static readonly _771 = new WeatherStatusEnum(771, 'squalls', WeatherIcon.MIST_DAY);
  static readonly _781 = new WeatherStatusEnum(781, 'tornado', WeatherIcon.MIST_DAY);

  // Group 800: Clear
  static readonly _800 = new WeatherStatusEnum(800, 'clear sky', WeatherIcon.CLEAR_DAY);

  // Group 80x: Clouds
  static readonly _801 = new WeatherStatusEnum(801, 'few clouds: 11-25%', WeatherIcon.FEW_CLOUDS_DAY);
  static readonly _802 = new WeatherStatusEnum(802, 'scattered clouds: 25-50%', WeatherIcon.SCATTERED_CLOUDS_DAY);
  static readonly _803 = new WeatherStatusEnum(803, 'broken clouds: 51-84%', WeatherIcon.BROKEN_CLOUDS_DAY);
  static readonly _804 = new WeatherStatusEnum(804, 'overcast clouds: 85-100%', WeatherIcon.BROKEN_CLOUDS_DAY);

  private static readonly MAP_BY_SYMBOL = new Map<number, WeatherStatusEnum>();
  static {
    for (let [key, value] of Object.entries(WeatherStatusEnum)) {
      if (key!=='MAP_BY_SYMBOL') {
        this.MAP_BY_SYMBOL.set(value.symbol, value);
      }
    }
  }

  private constructor(private symbol: number, private description: string, private weatherIcon: WeatherIcon){}

  public static getInstanceBySymbol(symbol: number): WeatherStatusEnum | undefined {
    return this.MAP_BY_SYMBOL.get(symbol);
  }

  public getSymbol(): number {
    return this.symbol;
  }

  public getDescription(): string {
    return this.description;
  }

  public getWeatherIcon(): WeatherIcon {
    return this.weatherIcon;
  }
}
