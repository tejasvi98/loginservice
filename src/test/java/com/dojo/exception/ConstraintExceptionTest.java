package com.dojo.exception;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ConstraintExceptionTest {

	@Test
	void testConstraintException() {
		ConstraintException constraintException = new ConstraintException("Contraint exception message here");
		assertNotNull(constraintException);
	}

}
