/**
 * 
 */
package analyzer.solr9.stanford;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author zhangcheng
 *
 */
public class StanfordAdapter implements Iterator<String> {
	

	private static StanfordAdapter adapter;
	private Iterator<String> tokens;
	private CRFClassifier<CoreLabel> segmenter;
	/**
	 * 
	 */
	private StanfordAdapter(Reader input, String modelDir) {
		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", modelDir);
		// props.setProperty("NormalizationTable", "data/norm.simp.utf8");
		// props.setProperty("normTableEncoding", "UTF-8");
		// below is needed because CTBSegDocumentIteratorFactory accesses it
		props.setProperty("serDictionary", modelDir + "/dict-chris6.ser.gz" + "," +  modelDir + "/dict-chris6.ser.gz");
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");

		segmenter = new CRFClassifier<CoreLabel>(props);
		segmenter.loadClassifierNoExceptions(modelDir + "/ctb.gz", props);
	}

	public static StanfordAdapter getInstance(Reader input, String modelDir) {
		if (null == adapter) {
			adapter = new StanfordAdapter(input, modelDir);
		}
		System.out.println("init adapter in" + Thread.currentThread().getName());
		return adapter;
	}
	
	public void reset(Reader input) {
		System.out.println("reset in" + Thread.currentThread().getName());
		String raw = null;
		try {
			StringBuilder bdr = new StringBuilder();
			char[] buf = new char[1024];
			int size = 0;
			while ((size = input.read(buf, 0, buf.length)) != -1) {
				String tempstr = new String(buf, 0, size);
				bdr.append(tempstr);
			}
			raw = bdr.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> list = segmenter.segmentString(raw);
		tokens = list.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return tokens.hasNext();
	}

	@Override
	public String next() {
		return tokens.next();
	}

}
