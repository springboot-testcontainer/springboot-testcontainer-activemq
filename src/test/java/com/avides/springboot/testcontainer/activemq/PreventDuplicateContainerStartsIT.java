package com.avides.springboot.testcontainer.activemq;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class PreventDuplicateContainerStartsIT extends AbstractIT
{
    @Test
    public void testPreventDuplicateContainerStarts()
    {
        assertEquals(1, dockerClient.listContainersCmd()
                .exec()
                .stream()
                .filter(container -> container.getLabels()
                        .containsKey("TESTCONTAINER_SERVICE") && container.getLabels().get("TESTCONTAINER_SERVICE").equals("activemq"))
                .count());
    }

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan("com.avides")
    static class TestConfiguration
    {
        // nothing
    }
}
