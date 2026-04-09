package com.project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.controller.CourseController;

@ExtendWith(MockitoExtension.class)
public class CourseControllerTest {

    private CourseController controller = new CourseController();

    @Test
    void testCoursesPage() {
        String view = controller.courses();
        assertThat(view).isEqualTo("courses");
    }

    @Test
    void testJavaPage() {
        assertThat(controller.java()).isEqualTo("java");
    }

    @Test
    void testPythonPage() {
        assertThat(controller.python()).isEqualTo("python");
    }

    @Test
    void testHtmlPage() {
        assertThat(controller.html()).isEqualTo("html");
    }

    @Test
    void testJavascriptPage() {
        assertThat(controller.js()).isEqualTo("javascript");
    }
}
