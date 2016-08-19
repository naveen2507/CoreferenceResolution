package com.ims.CorefrenceResolution;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * Read data file and extract markables and further stores them in markable files
 * @author Naveen
 *
 */
public class MarkableDataExtraction {
	
    private static final char L_PAREN    = '(';
    private static final char R_PAREN    = ')';
   
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {

            if      (s.charAt(i) == L_PAREN)   stack.push(L_PAREN);

         
            else if (s.charAt(i) == R_PAREN) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_PAREN) return false;
            }

            
            // ignore all other characters

        }
        return stack.isEmpty();
    }


    
    public void getMarkables(){
    	
    	
    	
    }
    
    
    
    public static String extractPairs(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {

            if      (s.charAt(i) == L_PAREN)   stack.push(L_PAREN);

                

          /*  else if (s.charAt(i) == R_PAREN) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_PAREN) return false;
            }

            else if (s.charAt(i) == R_BRACE) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_BRACE) return false;
            }

            else if (s.charAt(i) == R_BRACKET) {
                if (stack.isEmpty())        return false;
                if (stack.pop() != L_BRACKET) return false;
            }
*/
            // ignore all other characters

        }
        //return stack.isEmpty();
		return s;
    }

    
    
    
    


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MarkableDataExtraction obj = new MarkableDataExtraction();
		//String readFile = "D:\\Uni-MS\\Coref-Resolution\\programming-exercise\\data\\doc1-train-sample.txt";
		//String writeFile = "D:\\Uni-MS\\Coref-Resolution\\programming-exercise\\data\\markable-doc1-train-sample-tags.tsv";
		//String readFile = "D:\\Uni-MS\\Coref-Resolution\\programming-exercise\\data\\ontonotes-train.conll";
		//String writeFile = "D:\\Uni-MS\\Coref-Resolution\\programming-exercise\\data\\markable-ontonotes-train.conll-tags.tsv";
		String readFile;
		String writeFile;
		if(ApplicationDetails.TRAIN){
			readFile = ApplicationDetails.TRAIN_DATA;
			writeFile = ApplicationDetails.TRAIN_DATA_MARKABLES	;		
		}
		else{
			readFile = ApplicationDetails.TEST_DATA;
			writeFile = ApplicationDetails.TEST_DATA_MARKABLES	;		
		
		}
		obj.getMarkablesData(readFile, writeFile);
	}

	
	@SuppressWarnings("resource")
	public void getMarkablesData(String fileName,String writerFileName) throws IOException{
		BufferedReader br;
		FileWriter writer = new FileWriter(writerFileName);
        
        MarkableExtraction markableExtObj = new MarkableExtraction();
		String line;
		br = new BufferedReader(new FileReader(fileName));
		List<SentenceVO> sentenceList = new LinkedList<SentenceVO>();
		List<LineVO> lstLineInstance = null ;
		SentenceVO sentenceVO = null;
		int sentenceNumber = 0;
		while ((line = br.readLine()) != null) {
			if(line.startsWith("#end document")){
				sentenceVO.setListLineVO(lstLineInstance);
				markableExtObj.getMarkables(sentenceVO, writer);
				sentenceList.add(sentenceVO);
				sentenceNumber = 0;
				
			}
			else if(line.isEmpty()){
				sentenceVO.setListLineVO(lstLineInstance);
				markableExtObj.getMarkables(sentenceVO, writer);
				sentenceList.add(sentenceVO);
				lstLineInstance = new ArrayList<LineVO>();
				sentenceVO = new SentenceVO();
				sentenceNumber++;
			}
			else if(line.startsWith("#begin document")){
				sentenceNumber++;
				sentenceVO = new SentenceVO();
				lstLineInstance = new ArrayList<LineVO>();
				
			}
			String elem[] = line.split("  +");
			
			if(elem.length<12){
				continue;
			}
			
			LineVO lineInstance = new LineVO();
			lineInstance = lineInstance.getLine(elem);
			sentenceVO.setDocIndex(lineInstance.getDocID());
			sentenceVO.setSentIndex(sentenceNumber);
			lstLineInstance.add(lineInstance);
			/*
			if(lineInstance.getParsePos().contains("NP")){
				if(isBalanced(lineInstance.getParsePos())){
					NounPhraseVO np = new NounPhraseVO();
					np.setDocId(lineInstance.getDocID());
					np.setStartIndex(lineInstance.getWordNumber());
					np.setEndIndex(lineInstance.getWordNumber());
				}
				
				
			}*/
			
			
			
			
			
		}
			
		writer.flush();
		writer.close();
		
	}
}
