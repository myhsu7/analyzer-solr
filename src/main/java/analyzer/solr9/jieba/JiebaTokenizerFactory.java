package analyzer.solr9.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.util.Map;

public class JiebaTokenizerFactory extends TokenizerFactory {
	
	private String segMode;
	private String userDictPath;
	
	public JiebaTokenizerFactory(Map<String, String> args) {
		super(args);
        if (null == args.get("segMode")) {
        	segMode = SegMode.SEARCH.name();
        } else {
        	segMode = args.get("segMode");
        }
		if (null == args.get("userDict")) {
			userDictPath = null;
		} else {
			userDictPath = args.get("userDict");
		}
	}

	@Override
	public Tokenizer create(AttributeFactory arg0) {
		return new JiebaTokenizer(segMode, userDictPath);
	}

	public String getSegMode() {
		return segMode;
	}

	public void setSegMode(String segMode) {
		this.segMode = segMode;
	}

}
