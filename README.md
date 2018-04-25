# NFT Java Client

## Usage

[Please see ClientDemo.java](/src/main/java/com/example/ClientDemo.java)

## Add as dependency

https://jitpack.io/#kodgemisi/nft-java-client

```
	<dependency>
	    <groupId>com.github.kodgemisi</groupId>
	    <artifactId>nft-java-client</artifactId>
	    <version>${nft-client.version}</version>
	</dependency>

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

## Troubleshooting

`java.lang.NoClassDefFoundError: org/slf4j/LoggerFactory`

You should provide `org.slf4j:slf4j-api` jar in the classpath of your project because Slf4j dependency is given as `provided` for this library as follows:

```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-api</artifactId>
  <version>[1.7.0, 1.8.0)</version>
  <scope>provided</scope>
</dependency>
```