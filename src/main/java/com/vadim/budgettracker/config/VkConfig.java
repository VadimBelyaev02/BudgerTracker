package com.vadim.budgettracker.config;

import com.vadim.budgettracker.exception.VkApiException;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VkConfig {

    @Bean
    public VkApiClient vkApiClient() {
        TransportClient transportClient = new HttpTransportClient();
        return new VkApiClient(transportClient);
    }

    @Bean
    public GroupActor groupActor() {
        return new GroupActor(207444936, "0d6e6e316378eb1a65878e8770ff241519ec1b3fbbd5f51d4b44e980d73a4373ffc99033a61d681af7df6");
    }

    @Bean
    public Integer ts() {
        Integer ts;
        try {
            ts = vkApiClient().messages().getLongPollServer(groupActor())
                    .execute().getTs();
        } catch (ApiException | ClientException e) {
            throw new VkApiException("There was an exception during trying to configure vk api", e);
        }
        return ts;
    }
}
