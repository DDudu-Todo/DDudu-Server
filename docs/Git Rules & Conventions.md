# 💻Git Rules & Conventions

### ✔️ Commit message

##### 메시지 형식

- type{(detail)}: title (#issue)
    - `feat(api): kakao login api를 연결 (#123)`

##### type 종류

| type | 설명 |
| ---- | ---- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 변경이 없는 수정(formatting, missing semi colons 등) |
| refactor | 코드 수정 |
| chore | 빌드 관련 수정, 패키지 추가 등 |
| test | 테스트 코드 추가 및 리팩토링 |

##### title 규칙

- 제목은 50자를 넘기지 않는다.
- 마침표를 붙이지 않는다.
- 과거시제를 사용하지 않는다.
- 명령어로 작성한다.

---

### ✔️ Branches

##### main

배포 가능한 상태의 코드가 있는 브랜치

##### develop

개발이 진행되는 브랜치

- 모든 기능이 추가되고 버그가 수정되어 배포 가능한 안정적인 상태라면 develop 브랜치를 main 브랜치에 merge 한다.

---

### ✔️ Pull Request

##### PR 단위

- issue 1개 해결  = PR

##### PR Conventions

- 제목
    - type{(detail)}: title
        - ex. `feat(login): 로그인 기능을 구현`

- 본문
    - Issue
    - 구현 내용
        - 최대한 자세히 작성하기!
    - 이미지 (선택사항)