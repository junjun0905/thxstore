//package com.ssafy.thxstore.controller.member;
//
//import com.ssafy.thxstore.controller.common.AcceptanceTest;
//import com.ssafy.thxstore.controller.config.AppProperties;
//import com.ssafy.thxstore.controller.member.docs.UserDocumentation;
//import com.ssafy.thxstore.member.dto.request.LoginRequest;
//import com.ssafy.thxstore.member.dto.request.ModifyPatchMemberRequest;
//import com.ssafy.thxstore.member.dto.request.ModifyPutMemberRequest;
//import com.ssafy.thxstore.member.repository.MemberRepository;
//import com.ssafy.thxstore.member.service.AuthService;
//import com.ssafy.thxstore.member.service.UserService;
//import io.restassured.response.ExtractableResponse;
//import io.restassured.response.Response;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static io.restassured.RestAssured.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class UserControllerTest extends AcceptanceTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    AuthService authService;
//
//    @Autowired
//    AppProperties appProperties;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    void setUp(final WebApplicationContext webApplicationContext,
//               final RestDocumentationContextProvider restDocumentationContextProvider) {
//
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentationContextProvider))
//                .build();
//
//        this.memberRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("?????? ????????? ????????? ?????? ????????????.")
//    public void modifyPatchMember() throws Exception {
//        String email = "modifypatchmember@email.com";
//        generateMember(email,"modifypatchmember");
//        ModifyPatchMemberRequest modifyPatchMemberRequest = ModifyPatchMemberRequest.builder()
//                .id(1L)
//                .nickname("IU")
//                .password(null)
//                .build();
//
//        this.mockMvc.perform(patch("/user/")
//                .header("authorization", getExampleToken(email))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(modifyPatchMemberRequest)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andDo(UserDocumentation.modifyPatchMember());
//    }
//
//    @Test
//    @DisplayName("?????? ????????? ?????? ????????? ????????????.")
//    public void modifyPutMember() throws Exception {
//        String email = "modifyput@email.com";
//        generateMember(email,"modifyLatAndLot");
//        ModifyPutMemberRequest modifyPutMemberRequest = ModifyPutMemberRequest.builder()
//                .id(1L)
//                .lat(39.00)
//                .lon(130.00)
//                .build();
//
//        this.mockMvc.perform(put("/user/")
//                .header("authorization", getExampleToken(email))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(modifyPutMemberRequest)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andDo(UserDocumentation.modifyPutMember());
//    }
//
//    @Test
//    @DisplayName("?????? ????????? ????????? ????????????.")
//    public void deleteMember() throws Exception {
//        String email = "delete@gmail.com";
//        generateMember(email,"deleteMember");
//        this.mockMvc.perform(delete("/user/")
//                .header("authorization", getExampleToken("delete@gmail.com"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(email)))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andDo(UserDocumentation.deleteMember());
//    }
//
//    /**
//     * ????????? ??? LoginRequest??? ????????? ????????? ??? ??? ????????? ????????????.
//     * @param email ??????????????? ????????? ?????????
//     * @return ???????????? ????????? ?????? ???
//     */
//    private String getExampleToken(String email){
//        LoginRequest loginRequest = new LoginRequest(email, "password123");
//        ExtractableResponse<Response> response = requestLogin(loginRequest);
//        return response.body().jsonPath().getString("accessToken");
//    }
//
//    /**
//     * ????????? ????????? ??????.
//     * @param loginRequest ????????? ?????? dto
//     * @return ????????? ?????? ??????, ??????, ????????????, ?????? ??????
//     */
//    private static ExtractableResponse<Response> requestLogin(LoginRequest loginRequest) {
//        return given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(loginRequest)
//                .when()
//                .post("/api/auth/login/")
//                .then().log().all()
//                .extract();
//    }
//}
