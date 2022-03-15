package chapter4.item22;

public enum PhysicalConstants_enum {
	AVOGADROS_NUMBER(6.022_140_857e23),
	BOLTMANN_CONST(1.380_648_52e-23),
	ELECTROM_MASS(9.109_383_56e-31);

	private double value;

	PhysicalConstants_enum(double value) {
		this.value = value;
	}
}
