## 로컬 개발환경 설정

### 도커 컴포즈를 이용해 로컬 DB 환경 설치

```shell
docker-compose -p shortener -f ./docker/docker-compose-local.yml up -d
```

### arm64 이슈
```shell
docker-compose -p shortener -f ./docker/docker-compose-local-arm64.yml up -d
```