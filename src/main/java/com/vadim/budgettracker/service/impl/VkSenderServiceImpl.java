package com.vadim.budgettracker.service.impl;

import com.vadim.budgettracker.service.VkSenderService;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class VkSenderServiceImpl implements VkSenderService {

    private final VkApiClient vkApiClient;
    private final GroupActor groupActor;

    public VkSenderServiceImpl(VkApiClient vkApiClient, GroupActor groupActor) {
        this.vkApiClient = vkApiClient;
        this.groupActor = groupActor;
    }

    @Override
    public void sendMessage(String vkId, String message) {
        Random random = new Random();
        try {
            vkApiClient.messages()
                    .send(groupActor)
                    .message(message)
                    .userId(Integer.parseInt(vkId))
                    // you should send integer id
                    .randomId(random.nextInt(10000))
                    .execute();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

    }
}
