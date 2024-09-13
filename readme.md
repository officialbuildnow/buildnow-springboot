# 협력업체 사전검증 서비스

### description

종합건설사(Recruter)와 전문건설사(Applier)의 관계로 돌아가는 서비스로 잡코리아와 똑같음.

### 프로젝트 개발 프레임워크

스프링 부트 (java)/gradle

### API 문서

포스트맨 워크 스페이스 중 SpringBoot study에 있음

### 프로젝트 아키텍처

데이터베이스는 mysql 사용 -> 스키마의 ERD는 경석 도웅 ERDCloud에서 확인 가능하며 데이터베이스는 entity 폴더 내부에 구현해놓음

계정에 대한 것은 SpringSecurity를 활용한 JWT 방식을 사용하였으며, 계정은 ROLE_RECRUITER와 ROLE_APPLIER의 role을 가지게 된다. 이 role에 따라 LoginFilter에서 로직이 달라짐.

### 임시저장 기능 구현 방식

임시저장의 경우, TempSaved 엔티티에 저장하면서 최종 제출 전까지 이를 계속 업데이트하는 방식으로 구현함. 만약 제출을 하게 되면 TempSaved의 내용을 순회하면서 각 알맞는 엔티티로 저장됨

문서가 저장되는 것은 TempSaved의 자식 엔티티인 TempHandedOut에 저장됨. 최종 제출시에 마찬가지로 순회되면서 HandedOut 엔티티로 넘어감.

문서는 S3에 저장하고 반환되는 링크를 저장하는 방식으로 데이터베이스에 관리됨. 즉, TempHandedOut에 저장될 때 S3에 저장하면서 링크를 반환받으며 이 이후로는 문서는 링크로 관리됨.
