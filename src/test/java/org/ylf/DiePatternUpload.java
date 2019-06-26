package org.ylf;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class DiePatternUpload {
//
//    {
//        "computerType": {
//        "availability": true,
//            "createdBy": "string",
//            "createdDate": "2019-06-26T03:16:31.956Z",
//            "diePatterns": [
//        {}
//    ],
//        "id": 0,
//            "lastModifiedBy": "string",
//            "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//            "name": "string",
//            "value": "string"
//    },
//        "createdBy": "string",
//        "createdDate": "2019-06-26T03:16:31.956Z",
//        "customTemplates": [
//        {
//            "artboardHeight": 0,
//            "artboardWidth": 0,
//            "createdBy": "string",
//            "createdDate": "2019-06-26T03:16:31.956Z",
//            "customNumber": "string",
//            "customQuantity": 0,
//            "customState": {
//            "availability": true,
//                "createdBy": "string",
//                "createdDate": "2019-06-26T03:16:31.956Z",
//                "customTemplates": [
//            {}
//        ],
//            "id": 0,
//                "lastModifiedBy": "string",
//                "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                "name": "string",
//                "value": "string"
//        },
//            "designMaterials": [
//            {
//                "controlHeight": "string",
//                "controlWidth": "string",
//                "createdBy": "string",
//                "createdDate": "2019-06-26T03:16:31.956Z",
//                "customTemplate": {},
//                "fontBold": "NORMAL",
//                "fontItalic": "NORMAL",
//                "fontType": {
//                "availability": true,
//                    "createdBy": "string",
//                    "createdDate": "2019-06-26T03:16:31.956Z",
//                    "designMaterials": [
//                {}
//            ],
//                "id": 0,
//                    "lastModifiedBy": "string",
//                    "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                    "name": "string",
//                    "value": "string"
//            },
//                "id": 0,
//                "isMoveLayers": "string",
//                "lastModifiedBy": "string",
//                "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                "leftMargin": 0,
//                "materialType": {
//                "availability": true,
//                    "createdBy": "string",
//                    "createdDate": "2019-06-26T03:16:31.956Z",
//                    "designMaterials": [
//                {}
//            ],
//                "id": 0,
//                    "lastModifiedBy": "string",
//                    "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                    "name": "string",
//                    "value": "string"
//            },
//                "numberOfLayers": 0,
//                "rotationAngle": 0,
//                "textContext": "string",
//                "textFontColor": "string",
//                "textFontSize": "string",
//                "topMargin": 0
//            }
//      ],
//            "diePattern": {},
//            "fabricDesignMaterials": [
//            {
//                "createdBy": "string",
//                "createdDate": "2019-06-26T03:16:31.956Z",
//                "customTemplate": {},
//                "id": 0,
//                "lastModifiedBy": "string",
//                "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                "originJson": "string"
//            }
//      ],
//            "finishedCondition": {
//            "availability": true,
//                "createdBy": "string",
//                "createdDate": "2019-06-26T03:16:31.956Z",
//                "customTemplates": [
//            {}
//        ],
//            "id": 0,
//                "lastModifiedBy": "string",
//                "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                "name": "string",
//                "value": "string"
//        },
//            "highRatio": 0,
//            "id": 0,
//            "lastModifiedBy": "string",
//            "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//            "modelType": {
//            "availability": true,
//                "createdBy": "string",
//                "createdDate": "2019-06-26T03:16:31.956Z",
//                "customTemplates": [
//            {}
//        ],
//            "id": 0,
//                "lastModifiedBy": "string",
//                "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                "name": "string",
//                "value": "string"
//        },
//            "productionRenderingImageUrl": "string",
//            "taobaoNickname": "string",
//            "templateImageHeight": 0,
//            "templateImageWidth": 0,
//            "theRecipientName": "string",
//            "user": {
//            "activated": true,
//                "createdBy": "string",
//                "createdDate": "2019-06-26T03:16:31.956Z",
//                "email": "string",
//                "firstName": "string",
//                "id": 0,
//                "imageUrl": "string",
//                "langKey": "string",
//                "lastModifiedBy": "string",
//                "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//                "lastName": "string",
//                "login": "string",
//                "resetDate": "2019-06-26T03:16:31.956Z"
//        },
//            "wideRatio": 0
//        }
//  ],
//        "diePatternType": "string",
//        "diePatternimagePath": "string",
//        "id": 0,
//        "lastModifiedBy": "string",
//        "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//        "recommendedStatus": {
//        "availability": true,
//            "createdBy": "string",
//            "createdDate": "2019-06-26T03:16:31.956Z",
//            "diePatterns": [
//        {}
//    ],
//        "id": 0,
//            "lastModifiedBy": "string",
//            "lastModifiedDate": "2019-06-26T03:16:31.956Z",
//            "name": "string",
//            "value": "string"
//    }
//    }

    class LoginModel {
        private String username;
        private String password;
        private Boolean remeberMe;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Boolean getRemeberMe() {
            return remeberMe;
        }

        public void setRemeberMe(Boolean remeberMe) {
            this.remeberMe = remeberMe;
        }
    }

    private String auth;


    class UserAgentInterceptor implements ClientHttpRequestInterceptor {

        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            HttpHeaders headers = request.getHeaders();
            System.out.println(auth);
            headers.add(HttpHeaders.AUTHORIZATION, auth);
            return execution.execute(request, body);
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
            .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        restTemplate.setInterceptors(Collections.singletonList(new UserAgentInterceptor()));
        return restTemplate;
    }


    RestTemplate restTemplate;

    @Before
    public void login(){
        restTemplate = restTemplate();

        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("admin");
        loginModel.setPassword("admin");
        loginModel.setRemeberMe(true);
        ResponseEntity<LoginResponse> loginResponseResponseEntity = restTemplate.postForEntity("http://new-api.boyuanziben.cn/api/authenticate", loginModel, LoginResponse.class);

        String id_token = loginResponseResponseEntity.getBody().getId_token();
        auth = "Bearer "+id_token;
    }


    MinioClient minioClient;

    @Before
    public void minioLogin() throws Exception{
        // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
        minioClient = new MinioClient("http://minio.boyuanziben.cn/", "AZ2WRZNNGP8A5ETJ7DYD", "0tsUJlBEfArIMFyZ6mExvY91o+20TmGUhPwh7IY5");
    }



    @Test
    public void test() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {

                List<ComputerType> computerTypeList = restTemplate.exchange("http://new-api.boyuanziben.cn/api/computer-types?page=0&queryParams=%201&size=10000",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ComputerType>>() {}).getBody();

        //System.out.println(computerTypeList);

                List<DiePattern> diePatternList = restTemplate.exchange("http://new-api.boyuanziben.cn/api/die-patterns?page=0&queryParams=1&size=100000",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<DiePattern>>() {}).getBody();




//
//                ResponseEntity<ComputerType> forEntity = restTemplate.getForEntity("http://new-api.boyuanziben.cn/api/die-patterns?page=0&queryParams=1&size=100000", List<DiePattern.class>);

        String pathname = "D:\\app\\workspace\\java\\workspace\\root\\springboot-vue\\src\\test\\resources\\模具轮廓图";
        File f = new File(pathname);
        String[] list = f.list();
        List<String> strings = Arrays.asList(list);
        //System.out.println(strings);
        System.out.println(strings.size());
        List<DiePattern> dies = new ArrayList<>();




        for (int i = 0;i< strings.size();i ++) {
            String s1 = strings.get(i);
            String s = pathname + File.separator + s1;
            File ff = new File(s);
            String[] lists = ff.list();
            List<String> stringss = Arrays.asList(lists);
            for (int j = 0; j <stringss.size();j++){
                DiePattern diePattern = new DiePattern();
                ComputerType computerType = new ComputerType();
                computerType.setValue(s1);
                int i1 = computerTypeList.indexOf(computerType);
                if (i1>0){
                    computerType = computerTypeList.get(i1);
                }else {
                    System.err.println(computerType);
                }
                diePattern.setComputerType(computerType);
                String diePatternType = stringss.get(j);
                String diePatternType1 = diePatternType.substring(0,diePatternType.lastIndexOf("."));
                diePattern.setFileName(s+File.separator+diePatternType);

                diePattern.setDiePatternType(diePatternType1);
                dies.add(diePattern);
            }
        }
        System.out.println(diePatternList);
        System.out.println(dies.size());

        dies.removeAll(diePatternList);

        System.out.println(dies.size());

        for (int i =0;i<dies.size();i++){

            String s = UUID.randomUUID().toString();
            DiePattern diePattern = dies.get(i);

            // 使用putObject上传一个文件到存储桶中。
            String objectName = s + ".png";
            minioClient.putObject("die-pattern", objectName, diePattern.getFileName());
            diePattern.setDiePatternimagePath("/die-pattern/"+objectName);
            ResponseEntity<DiePattern> diePatternResponseEntity = restTemplate.postForEntity("http://new-api.boyuanziben.cn/api/die-patterns", diePattern, DiePattern.class);
            System.out.println(diePatternResponseEntity.getStatusCode());
            System.out.println(diePatternResponseEntity.getBody());
        }


        // 计算机类型
        // http://new-api.boyuanziben.cn/api/computer-types?page=0&queryParams=%201&size=10000;

//        List<DiePattern> response = restTemplate.exchange("http://new-api.boyuanziben.cn/api/die-patterns?page=0&queryParams=1&size=100000",
//            HttpMethod.GET,
//            null,
//            new ParameterizedTypeReference<List<DiePattern>>() {}).getBody();
//
//        System.out.println(response);
//        System.out.println(response.size());


//        ResponseEntity<DiePattern> forEntity = restTemplate.getForEntity("http://new-api.boyuanziben.cn/api/die-patterns?page=0&queryParams=1&size=100000", List<DiePattern.class>);





        // 获取已知刀模图
        // http://new-api.boyuanziben.cn/api/die-patterns?page=0&queryParams=1&size=100000


        // 添加刀模图
        // http://new-api.boyuanziben.cn/api/die-patterns



        // restTemplate.postForEntity("http://new-api.boyuanziben.cn/api/die-patterns",)



//        strings.forEach(dir -> {
//            DiePattern diePattern = new DiePattern();
//            diePattern.setComputeType(dir);
//
//        });
    }
}
