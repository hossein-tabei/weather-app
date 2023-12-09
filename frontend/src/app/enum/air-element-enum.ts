export class AirElementEnum {
  static readonly PM2_5 = new AirElementEnum('PM2.5','PM', 2.5);
  static readonly PM10 = new AirElementEnum('PM10','PM', 10);
  static readonly SO2 = new AirElementEnum('SO2','SO', 2);
  static readonly NO2 = new AirElementEnum('NO2','NO', 2);
  static readonly O3 = new AirElementEnum('O3','O', 3);
  static readonly CO = new AirElementEnum('CO','CO', undefined);

  private static readonly MAP_BY_SYMBOL = new Map<string, AirElementEnum>();
  static {
    this.MAP_BY_SYMBOL.set(this.PM2_5.symbol, this.PM2_5);
    this.MAP_BY_SYMBOL.set(this.PM10.symbol, this.PM10);
    this.MAP_BY_SYMBOL.set(this.SO2.symbol, this.SO2);
    this.MAP_BY_SYMBOL.set(this.NO2.symbol, this.NO2);
    this.MAP_BY_SYMBOL.set(this.O3.symbol, this.O3);
    this.MAP_BY_SYMBOL.set(this.CO.symbol, this.CO);
  }

  private constructor(private symbol: string, private literalSymbol: string, private numericSymbol: number|undefined){}

  public static getInstanceBySymbol(symbol: string): AirElementEnum | undefined {
    return this.MAP_BY_SYMBOL.get(symbol);
  }

  public getSymbol(): string {
    return this.symbol;
  }

  public getLiteralSymbol(): string|undefined {
    return this.literalSymbol;
  }

  public getNumericSymbol(): number|undefined {
    return this.numericSymbol;
  }
}
