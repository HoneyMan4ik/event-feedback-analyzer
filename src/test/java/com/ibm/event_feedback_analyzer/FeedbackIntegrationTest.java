package com.ibm.event_feedback_analyzer;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class FeedbackIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception{

        String eventRequestJson = """
                {
                "title": "EAPL 2025",
                "description": "Mykolas Romeris University"
                }
                """;

        MvcResult eventResult = mockMvc.perform(post("/events").contentType(MediaType.APPLICATION_JSON).content(eventRequestJson)).
                andExpect(status().isOk()).andExpect(jsonPath("$.id").exists()).andReturn();

        String eventJson = eventResult.getResponse().getContentAsString();
        int eventId = JsonPath.read(eventJson, "$.id");

        String feedbackRequestJson = """
                {
                    "text": "Great conference"
                }
                """;

        mockMvc.perform(post("/events/" + eventId + "/feedback").contentType(MediaType.APPLICATION_JSON).content(feedbackRequestJson)).
                andExpect(status().isOk()).andExpect(jsonPath("$.sentiment").exists()).andExpect(jsonPath("$.text").value("Great conference"));

        mockMvc.perform(get("/events/" + eventId + "/summary")).andExpect(status().isOk()).andExpect(jsonPath("$").isNotEmpty());
    }
}
