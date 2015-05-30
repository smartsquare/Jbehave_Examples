package bi.jug.jbehave.steps;

import org.jbehave.core.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeforeAndAfterSteps {

    private Logger LOG = LoggerFactory.getLogger( this.getClass().getName() );

    @BeforeScenario( uponType = ScenarioType.ANY )
    public void beforeAnyScenario() {

        // LOG.info( "Before each scenario" );

    }

    @BeforeStory
    public void beforeStory() {
        // LOG.info( "Before each story" );
    }

    @AfterScenario( uponType = ScenarioType.ANY )
    public void afterAnyScenario() {

        // LOG.info( "After each scenario" );

    }

    @AfterStory
    public void afterStory() {
        // LOG.info( "After each story" );
    }

}
