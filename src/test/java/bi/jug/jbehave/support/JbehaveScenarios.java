package bi.jug.jbehave.support;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import bi.jug.jbehave.steps.BeforeAndAfterSteps;
import bi.jug.jbehave.steps.CustomerShoppingCartSteps;
import bi.jug.jbehave.steps.RedeemPromotionCodeSteps;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith( JUnitReportingRunner.class )
public class JbehaveScenarios
    extends JUnitStories {

    private Object[] context = {
        new BeforeAndAfterSteps(),
        new RedeemPromotionCodeSteps(),
        new CustomerShoppingCartSteps()
    };

    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put( "decorateNonHtml", "true" );

        return new MostUsefulConfiguration()
            .useStoryReporterBuilder(
            new StoryReporterBuilder()
                .withViewResources( viewResources )
                .withFormats( Format.CONSOLE, Format.TXT, Format.HTML, Format.XML )
                .withFailureTrace( true ).withFailureTraceCompression( true ) );
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory( configuration(), context );
    }

    @Override
    protected List<String> storyPaths() {

        List<String> includes = Arrays.asList( "**/*.story" );
        List<String> excludes = null;

        return new StoryFinder().findPaths( codeLocationFromClass( this.getClass() ).getFile(),
            includes,
            excludes );
    }
}
