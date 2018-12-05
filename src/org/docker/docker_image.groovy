/* ************************************************************************
 * Copyright 2018 Advanced Micro Devices, Inc.
 * ************************************************************************ */
package org.docker


class docker_image implements Serializable
{
    docker.image image
    docker_image(docker_data docker_args, project_paths paths)
    {
	String build_image_name = "build-rocblas-hip-artifactory"
	def build_image = null

	dir( paths.project_src_prefix )
	{
	    def user_uid = sh( script: 'id -u', returnStdout: true ).trim()

	    // Docker 17.05 introduced the ability to use ARG values in FROM statements
	    // Docker inspect failing on FROM statements with ARG https://issues.jenkins-ci.org/browse/JENKINS-44836
	    // build_image = docker.build( "${paths.project_name}/${build_image_name}:latest", "--pull -f docker/${build_docker_file} --build-arg user_uid=${user_uid} --build-arg base_image=${from_image} ." )

	    // JENKINS-44836 workaround by using a bash script instead of docker.build()
	    sh "docker build -t ${paths.project_name}/${build_image_name}:latest -f docker/${docker_args.build_docker_file} ${docker_args.docker_build_args} --build-arg user_uid=${user_uid} --build-arg base_image=${docker_args.from_image} ."
	    build_image = docker.image( "${paths.project_name}/${build_image_name}:latest" )
	}
    }
}