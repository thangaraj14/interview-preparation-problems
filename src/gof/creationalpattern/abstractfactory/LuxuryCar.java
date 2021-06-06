package creationalpattern.abstractfactory;

class LuxuryCar extends Car {
	LuxuryCar(Location location) {
		super(CarType.LUXURY, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Connecting to luxury car");
	}
}

class MicroCar extends Car {
	MicroCar(Location location) {
		super(CarType.MICRO, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Connecting to Micro Car ");
	}
}

class MiniCar extends Car {
	MiniCar(Location location) {
		super(CarType.MINI, location);
		construct();
	}

	@Override
	void construct() {
		System.out.println("Connecting to Mini car");
	}
}