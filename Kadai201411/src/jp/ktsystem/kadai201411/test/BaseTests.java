package jp.ktsystem.kadai201411.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.TestCase;

public abstract class BaseTests extends TestCase {
	protected static final String SAMPLE_RESULT_PATH = "..\\Kadai201411\\testfiles\\";

	protected void compareToSample(InputStream sampleStream, InputStream resultStream) throws IOException {
		int sample = 0;

		do {
			sample = sampleStream.read();
			int result = resultStream.read();
			assertEquals(sample, result);
		} while (sample >= 0);
	}

	protected InputStream createSampleStream(String aFilename) throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(
				SAMPLE_RESULT_PATH + aFilename));
	}

}
