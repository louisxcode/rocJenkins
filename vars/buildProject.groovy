import org.amd.project.project_paths;
import org.amd.docker.rocDocker;

def call(String nodeLogic, project_paths paths, rocDocker docker, Closure body)
{   
    node ( nodeLogic )
    {
        stage ("Checkout source code")
        {
            echo "Saa"
            build.checkout(paths)
        }
        
        stage ("Build Docker Container")
        {
            echo "Boo"
            // Create/reuse a docker image that represents the rocprim build environment
            //docker.stage = stage
            docker.buildImage(this)
            // Print system information for the log
            docker.image.inside( docker.runArgs, docker.insideClosure )    
        }
    
    
    body()
    }

}
