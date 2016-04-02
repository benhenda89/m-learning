package com.atec.learning.track.domain;

import java.util.HashMap;
import java.util.Map;


public enum UserNote {

		VIEW("01"),
		LIKE("02"),
		RATE("03"),
		ACHAT("04");
		
		private String value;
		private static  Map<String	, UserNote> valueMap = new HashMap<String, UserNote>();
		
		private UserNote (String value){
			this.value = value;
		}
		
		static{
			for(UserNote note : UserNote.values()){
				valueMap.put(note.value, note);
			}
		}
		
		public static UserNote getInstanceByCode(String value){
			return valueMap.get(value);
		}

}
