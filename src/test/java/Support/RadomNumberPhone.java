package Support;

import org.apache.commons.lang3.RandomStringUtils;

public class RadomNumberPhone {
	public String radomNumberPhoneValid() {
		String key1 = "35789";
		String key2 = "0123456789";
		String num1 = RandomStringUtils.random(1, key1);
		String num2 = RandomStringUtils.random(8, key2);
		String phoneNumberValid = "0" + num1 + num2;
		return phoneNumberValid;
	}

	public String radomNumberPhoneInValid() {
		String key1 = "123456789";
		String key2 = "01246";
		String key3 = "0123456789";
		String num1 = RandomStringUtils.random(1, key1);
		String num2 = RandomStringUtils.random(1, key2);
		String num3 = RandomStringUtils.random(8, key3);
		String phoneNumberInvalid = num1 + num2 + num3;
		return phoneNumberInvalid;
	}

}
