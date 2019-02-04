/* ************************************************************************
 * Copyright 2018-2019 Advanced Micro Devices, Inc.
 * ************************************************************************ */

import com.amd.project.*
import com.amd.docker.dockerNodes


import java.nio.file.Path;

class dockerNodes implements Serializable
{
    def dockerArray
    
    dockerNodes(def jenkinsLabels = ['gfx900'], project_paths paths)
    {
        jenkinsLabels.each
        {
            dockerArray[it] = new rocDocker(
                            buildImageName:'build-rocprim-hip-artifactory',
                            paths: paths,
                            jenkinsLabel: it
                        )
        }
    }
}