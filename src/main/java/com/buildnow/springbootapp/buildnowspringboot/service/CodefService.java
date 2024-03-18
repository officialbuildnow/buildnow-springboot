package com.buildnow.springbootapp.buildnowspringboot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codef.api.EasyCodef;
import io.codef.api.EasyCodefServiceType;
import io.codef.api.EasyCodefUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service

public class CodefService {
    @Value("${codef.publicKey}") String publicKey;
    @Value("${codef.clientId}") String clientId;

    @Value("${codef.clientSecret}") String clientSecret;
//    public String registerUser() throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
//        // 개인 보유카드 조회(Connected ID 사용)
//
///** #1.쉬운 코드에프 객체 생성 및 클라이언트 정보 설정 */
//        EasyCodef codef = new EasyCodef();
//        codef.setClientInfo(clientId, clientSecret);
//        codef.setPublicKey(publicKey);
//
//
//
///** #5.요청 파라미터 설정 - 각 상품별 파라미터를 설정(https://developer.codef.io/products) */
//        HashMap<String, Object> parameterMap = new HashMap<String, Object>();
//        parameterMap.put("organization", "0002");
//        parameterMap.put("issueNo", "");
//        parameterMap.put("inquiryType", "0");
//
///** #6.코드에프 정보 조회 요청 - 서비스타입(API:정식, DEMO:데모, SANDBOX:샌드박스) */
//        String productUrl = "/v1/kr/card/p/account/card-list";	// (예시)개인 보유카드 조회 URL
//        String result = codef.requestProduct(productUrl, EasyCodefServiceType.SANDBOX, parameterMap);
//
//
//
///**	#7.코드에프 정보 결과 확인	*/
//        System.out.println(result);
//
//        HashMap<String, Object> responseMap = new ObjectMapper().readValue(result, HashMap.class);
//        HashMap<String, Object> resultMap = (HashMap<String, Object>)responseMap.get("result");
//
//        assertEquals("코드에프 상품 요청 결과 실패(반환된 코드와 메시지 확인 필요)", "CF-00000", (String)resultMap.get("code"));
//    }

}
