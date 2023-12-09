export class WeatherStatusEnum {
  static readonly SUNNY = new WeatherStatusEnum('Clear','sunny');
  static readonly COUDY = new WeatherStatusEnum('Cloudy','cloud');

  private static readonly MAP_BY_SYMBOL = new Map<string, WeatherStatusEnum>();
  static {
    this.MAP_BY_SYMBOL.set(this.SUNNY.symbol, this.SUNNY);
    this.MAP_BY_SYMBOL.set(this.COUDY.symbol, this.COUDY);
  }

  private constructor(private symbol: string, private iconSymbol: string){}

  public static getInstanceBySymbol(symbol: string): WeatherStatusEnum | undefined {
    return this.MAP_BY_SYMBOL.get(symbol);
  }

  public getSymbol(): string {
    return this.symbol;
  }

  public getIconSymbol(): string {
    return this.iconSymbol;
  }
}
