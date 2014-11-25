package kuvaldis;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromURL;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.steps.ScanningStepsFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;

public class JBehaveRunner extends JUnitStories {

    public JBehaveRunner() {
        Configuration configuration = new MostUsefulConfiguration().useStoryLoader(new LoadFromURL());
        useConfiguration(configuration);
        useStepsFactory(new ScanningStepsFactory(configuration, "kuvaldis")  {
            private Map<Class<?>, Object> instances = new HashMap<>();
            @Override
            public Object createInstanceOfType(Class<?> type) {
                if (instances.containsKey(type)) {
                    return instances.get(type);
                }
                Object instance = super.createInstanceOfType(type);
                instances.put(type, instance);
                return instance;
            }
        });
    }

    @Override
    protected List<String> storyPaths() {
        String codeLocation = codeLocationFromClass(this.getClass()).getFile();
        return new StoryFinder().findPaths(codeLocation, asList("**/*.story"),
                asList(""), "file:" + codeLocation);
    }

}
