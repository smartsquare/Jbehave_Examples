package bi.jug.jbehave.support;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import bi.jug.jbehave.steps.BeforeAndAfterSteps;
import bi.jug.jbehave.steps.CustomerLoyaltyPointsSteps;
import bi.jug.jbehave.steps.CustomerShoppingCartSteps;
import bi.jug.jbehave.steps.RedeemPromotionCodeSteps;
import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith( JUnitReportingRunner.class )
public class JbehaveScenarios
    extends JUnitStories {

    private Object[] context = {
        new BeforeAndAfterSteps(),
        new RedeemPromotionCodeSteps(),
        new CustomerShoppingCartSteps(),
        new CustomerLoyaltyPointsSteps()
    };

    public JbehaveScenarios() {
        configuredEmbedder().embedderControls()
            .doGenerateViewAfterStories( true )
            .doIgnoreFailureInStories( false )
            .doIgnoreFailureInView( true )
            .doFailOnStoryTimeout( false );

        configuredEmbedder().useMetaFilters( Arrays.asList( "-skip" ) );
    }

    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put( "decorateNonHtml", "true" );

        if ( super.hasConfiguration() ) {
            return super.configuration();
        }

        Class<? extends Embeddable> embeddableClass = this.getClass();
        return new MostUsefulConfiguration()
            .useStoryLoader( new LoadFromClasspath( embeddableClass ) )
            .useStoryReporterBuilder(
                new StoryReporterBuilder()
                    .withDefaultFormats()
                    .withFormats( Format.CONSOLE, Format.TXT, Format.HTML, Format.XML )
                    .withFailureTrace( true ).withFailureTraceCompression( true ) );
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory( configuration(), context );
    }

    @Override
    protected List<String> storyPaths() {
        String codeLocation = CodeLocations.codeLocationFromClass( this.getClass() ).getFile();

        List<String> include = Arrays.asList( "**/*.story" );
        List<String> exclude = null;

        return new StoryFinder().findPaths( codeLocation, include, exclude );
    }
}
