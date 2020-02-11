# APACHE SHARDING-SPHERE 
- 오픈소스 분산 데이터베이스 솔루션으로 `Sharding-JDBC`, `Sharding-Proxy`, `Sharding-Sidecar` 등의 독립적인 제품을 포함하고 있음
- 분산 트랜잭션 및 데이터베이스 오케스트레이션에 대한 기능을 제공
![enter image description here](https://shardingsphere.apache.org/document/current/img/shardingsphere-hybrid.png)
- 세 가지 솔루션 중 `Sharding-JDBC`에 대해 살펴볼 예정

## Sharding?
- 하나의 집중된 노드에 모든 데이터를 저장하는 기존 솔루션은 성능, 가용성 및 운영 비용이라는 세 가지 측면에서 대규모 인터넷 데이터 시나리오의 요구 사항을 거의 충족시키지 못했습니다.
- 관계형 데이터베이스는 주로 B + 트리 인덱스를 사용합니다. 데이터 양이 임계 값을 초과하면 인덱스가 깊을수록 디스크 IO 액세스 수가 증가하여 쿼리 성능이 저하됩니다.
- 동시에 동시성 요청이 많으면 중앙 데이터베이스가 시스템의 가장 큰 제한이됩니다.
- 그러나 단일 데이터 노드 또는 간단한 마스터-슬레이브 구조는 이러한 압력을 받기가 점점 더 어려워졌습니다. 따라서 데이터베이스 가용성은 전체 시스템의 핵심이되었습니다.
- 데이터베이스 인스턴스의 데이터가 임계 값을 초과하면 DBA의 운영 압력도 증가합니다. 증가하는 데이터 양으로 인해 데이터 백업 및 데이터 복구에 소요되는 시간을보다 제어 할 수 없게됩니다
- 샤딩은 특정 데이터베이스에 따라 하나의 데이터베이스에서 데이터를 분할하여 여러 테이블과 데이터베이스에 저장하여 성능과 가용성을 향상시키는 것을 말합니다

### Vertical Sharding
![vertical_sharding](https://i.imgur.com/5aauDmX.png)
- 테이블별로 별도의 데이터베이스에 영속화
- `Vertical Sharding` 후에도 테이블의 데이터 양이 많아지면 여전히 임계 값을 초과하게 되며 이는 `Horizontal Sharding`으로 추가 처리해야 하는 것이 좋음

### Horizontal Sharding
![horizontal_sharding](https://i.imgur.com/EIsF68I.png)
- 가로 샤딩(`transverse sharding`)이라 부르기도 하며, 특정 필드를 통해 특정 규칙에 따라 여러 데이터베이스 또는 테이블로 데이터를 분류하며 각 샤딩에는 데이터의 일부만 포함.

## Sharding-JDBC
- `JPA`, `Hibernate`, `Mybatis`, `Spring JDBC Template`과 같은 JDBC 기반 구현체 또는 JDBC를 직접 사용 가능.
- `DBCP`, `HikariCP`와 같은 데이터베이스 Connection Pool 구현체 지원
- `MySQL`, `Oracle`, `SQLServer`, `PostgreSQL` 등의 데이터베이스 지원
- `Java`, `YAML`, `Spring XML Config` 및 `Spring Boot Starter`의 네 가지 방법으로 구성 가능
- `io.shardingsphere`라는 구현체도 존재하는데, apache의 구현체와 거의 유사. 정확한 차이점을 아직 잘...

![Sharding-JDBC](https://i.imgur.com/2vmVbYE.png)
- 위 그림과 같이, 어플리케이션 레벨에서는 논리적으로 하나의 테이블을 바라보는 형태처럼 보일 수 있으나, 실제 개별 데이터베이스에 대한 커넥션을 유지하고 있는 형태
- 지정한 샤딩 필드에 규칙을 매핑해줘야 하는데, 샤딩은 =,> =, <=,>, <, BETWEEN 및 IN을 통한 샤딩 알고리즘으로 수행 할 수 있음.

### Sharding-Algorithm & Sharding-Strategy
- 위에서 말한대로 `특정 필드`와 `특정 규칙`에 대한 정의가 필요한데, 이에 대한 정의는 샤딩 알고리즘과 전략에서 지정할 수 있다.
- 기본 알고리즘은 구현체에서 제공해주며 디테일한 샤딩 전략과 키 매핑이 필요하다면 아래 테이블을 참고하여 해당 전략을 직접 구현할 수도 있다.
#### 알고리즘 기준으로 샤딩 전략 매핑
|알고리즘|전략|설명|
|-------------------------------|---------------------------|----------------------------------------------------------------|
| PreciseShardingAlgorithm      | StandardShardingStrategy  |단일 샤딩 키로 = 및 IN이 사용.                                        |
| RangeShardingAlgorithm        | StandardShardingStrategy  |단일 샤딩 키로 BETWEEN AND、>、<、>=、<= 등과 같이 범위로 지정해야 할 경우 사용.|
| ComplexKeysShardingAlgorithm  | ComplexShardingStrategy   |샤딩 필드를 복합키로 구성해야 할 경우 사용.                               |
| HintShardingAlgorithm         | HintShardingStrategy      |데이터베이스 힌트를 기준으로 샤딩해야 할 경우 사용.                          |
#### 샤딩 전략 기준
|전략|알고리즘|설명|
|-|-|-|
| StandardShardingStrategy      | PreciseShardingAlgorithm, RangeShardingAlgorithm   |단일 샤딩 키 기준의 전략 |
| ComplexShardingStrategy       | ComplexShardingStrategy |단일 샤딩 키로 BETWEEN AND、>、<、>=、<= 등과 같이 범위로 지정해야 할 경우 사용.|
| InlineShardingStrategy        | X | - Groovy 표현식을 사용하여 전략을 매핑. <br/> - 예시) t_user_$->{u_id % 8} ->  t_user_0 to t_user_7 |
| HintShardingStrategy          | X | 데이터베이스 힌트를 기준으로 샤딩해야 할 경우 사용. |
| NoneShardingStrategy          | X | 참조하는 전략이 없을 경우 사용 |

- auto-increment와 같은 DB에서 생성해주는 자동 증가 값을 사용할 경우, 샤딩 알고리즘이 제대로 동작하지 않을 수 있다고 하며, 서버에서 생성한 고유 값을 사용하는 것이 일반적. [SNOWFLAKE](https://charsyam.wordpress.com/2012/12/26/%EC%9E%85-%EA%B0%9C%EB%B0%9C-global-unique-object-id-%EC%83%9D%EC%84%B1-%EB%B0%A9%EB%B2%95%EC%97%90-%EB%8C%80%ED%95%9C-%EC%A0%95%EB%A6%AC/)와 같은 값이 사용되며, 링크를 참고하자.

- 어플리케이션 레벨에서 직접 혹은 구현체가 작성한 SQL 구문은 Rewrite Engine에 의해 실제 물리 데이터베이스 노드에서 필요한 SQL의 형태로 변환됨.
```sql
-- 샤딩키가 order_id
SELECT order_id FROM t_order WHERE order_id=1;

-- 샤딩 전략과 알고리즘에 의해 실제 데이터베이스 노드에 전달되는 SQL은 아래와 같다.
SELECT order_id FROM t_order_1 WHERE order_id=1;
```

[SHARDING-JDBC 예제](https://juejin.im/post/5d9fe175f265da5bba417a28)

## 예제 SQL
```sql
create schema ds0; 
create schema ds1;

create table ds0.t_order
(
	id bigint not null
		primary key,
	name varchar(255) null comment 'Name',
	type varchar(255) null comment 'type',
	gmt_create timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'Creation time'
);

create table ds1.t_order
(
	id bigint not null
		primary key,
	name varchar(255) null comment 'Name',
	type varchar(255) null comment 'type',
	gmt_create timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment 'Creation time'
);

-- https://dev.mysql.com/doc/employee/en/employees-installation.html
```