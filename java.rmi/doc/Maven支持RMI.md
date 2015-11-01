Maven支持RMI
=======================================================

**转自：[https://onenesta.wordpress.com/2009/08/03/maven支持rmi/](https://onenesta.wordpress.com/2009/08/03/maven%E6%94%AF%E6%8C%81rmi/)**

Maven插件
-------------------------------------------------------

<http://mojo.codehaus.org/rmic-maven-plugin/>

For example:
-------------------------------------------------------

```
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>rmic-maven-plugin</artifactId>
            <version>1.2.1</version>
            <executions>
                <execution>
                    <id>rmic-process-classes</id>
                    <goals>
                        <goal>rmic</goal>
                    </goals>
                    <configuration>
                        <iiop>false</iiop>
                        <verbose>true</verbose>
                        <outputDirectory>${project.build.outputDirectory}</outputDirectory>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```