package creationalpattern.abstractfactory;
class INDIACarFactory {
	static Car buildCar(CarType model) {
		Car car = null;
		switch (model) {
		case MICRO:
			car = new MicroCar(Location.INDIA);
			break;

		case MINI:
			car = new MiniCar(Location.INDIA);
			break;

		case LUXURY:
			car = new LuxuryCar(Location.INDIA);
			break;

		default:
			break;

		}
		return car;
	}
}