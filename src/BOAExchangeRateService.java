public class BOAExchangeRateService implements ExchangeRateService
{
//    public Money convert(Money amount, String toCurrency) {
//        return new Money(amo)
//    }
    @Override
    public double getRate(String fromCurrency, String toCurrency) {
        return Math.random()+1.0;
    }
}
