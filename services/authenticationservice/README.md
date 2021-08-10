   master ✚ ● ?  docker buildx build -t arunstiwari/authenticationservice:1.0 --platform linux/amd64 --push .

docker run --rm --name authenticationservice -e "awsSecretKey
=Dx//IPkKDAZplbWrjFD32422SAGHG13bYPljucAJtRRPq/TR4&Pg1fRvVkR"  -e "awsAccessKey=AKIAFDEEW12E4FDFDfdfdfdfJC4LMWF3HBQYWWA" -e "SPRING_PROFILES_ACTIVE=cloud"  -p8000:80 authenticationservice:1.0