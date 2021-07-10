# Concept
- Page: 사용자가 보게되는 페이지로, module의 집합
  - version: 페이지 별로 여러 버전을 둔다. AB테스트 진행 시 앱 버전별로 적용되는 페이지가 달라지기 때문.
- Module: 특정 기능을 수행하는 엘리먼트 ex) product list module, banner module 등
- Provider 
- Property
- FluentApi: api를 동시에 여러개 호출하기 위한 시스템(존재하지 않음)
                

# front platform Architecture
PageCommandService: page cud
PageQueryService: page r

PageModuleCommandService: module cud
PageModuleQueryService: module r

PageProperty


    
# Note
- 불필요 코드
  - Canary 여부 확인
  - isDeleted <- 왜 필요? 
  - propertyMapping - 시스템 어드민한테만 필요한 기능임 
  - fluentApi
  - provider - 굳이 나눌 필요 없어 보임
    
- 나중에 추가할 코드
  - history
  - cache
  - 
  - 
  
- 새로 개발해야할 코드
  - 모듈 버저닝
    
# overall architecture 
 ** 처음엔 동일 프로젝트내에서 모듈을 분리하는 식으로하고 나중에는 마이크로서비스로 전환
- front gateway (spring cloud gateway 사용해야할 듯?) : front 페이지에 처음 접근할 떄 게이트웨이를 먼저 거친다. 여기서 front platform으로부터 page정보를 가져와 listing 시스템에 쿼리한다
- front platform : admin 시스템 (module 조합해서 페이지 구성)
- front listing : admin에서 조합된 페이지를 실제로 화면에 보여주는 시스템
  - listing 시스템에서 받는 쿼리는 (page 아이디 / 유저 아이디(이건 필요한가...) / 커뮤니티 아이디)로 구성되어, 어떤 데이터를 page 레이아웃에 매핑할 것인지 결정함 
  - 즉, 레이아웃과 데이터를 조합해주는 시스템
  - 나중엔 이것도 나눠야할 듯 (게시판형 listing system, 쇼핑몰형 listing system 등 ?)
- front cms : admin 컨텐츠 관리 시스템 (타이틀 이미지를 넣거나 등등) 
  - 이건 front platform에 통합해도 될 것 같음
- cdn : cms에 등록하는 파일이나 사용자가 업로드 하는 파일(이미지 등)을 등록하면 실제로는 여기를 거쳐야 함 (이미지 크기를 줄이거나 그런 활동 수행??)
  - 이미지를 섬네일화 시켜서 부하를 줄이도록 함


** 선수지식
- spring cloud
- gradle
- microservice architecture
- jpa
- cdn 구성방법
