export class AirElementEnum {
  static readonly PM2_5 = new AirElementEnum('PM2.5','PM', 2.5);
  static readonly PM10 = new AirElementEnum('PM10','PM', 10);
  static readonly SO2 = new AirElementEnum('SO2','SO', 2);
  static readonly NO2 = new AirElementEnum('NO2','NO', 2);
  static readonly O3 = new AirElementEnum('O3','O', 3);
  static readonly CO = new AirElementEnum('CO','CO', undefined);

  private static readonly MAP_BY_SYMBOL = new Map<string, AirElementEnum>();
  static {
    for (let [key, value] of Object.entries(AirElementEnum)) {
      if (key!=='MAP_BY_SYMBOL') {
        this.MAP_BY_SYMBOL.set(value.symbol, value);
      }
    }
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
