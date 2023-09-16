## 1. Naming Convention

- snake_case
    - folders
    - files
- camelCase
  - functions
  - variables
  - constants
- PascalCase
  - classes
  - extensions
  - mixins
> Enum
>   - names : PascalCase
>   - parameters : camelCase

## 2. Comment Convention

```
1. 최초작성자, 날짜
2. 파일이름
- 기능
- 내용
3. 수정목록
- 날짜, 사람, 수정사항
-
```
- example
```
1. 이상린 (2023.07.19)
2. main.dart
- 기능 : 메인화면 출력
- 내용 : 각각 다른 파일에 있는 dart파일들 실행
1. 수정목록
- 이상린 (2023.08.11) : 회원가입 파일 추가
- ...
```

## 3. Write Every Type Everywhere

`add(a,b) => a+b;` -> wrong  
`int add(int a, int b) => a+b;` -> right