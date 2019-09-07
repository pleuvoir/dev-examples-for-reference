package io.github.pleuvoir;

import io.github.pleuvoir.config.BatchConfiguration;
import io.github.pleuvoir.config.DatasourceConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BatchConfiguration.class, DatasourceConfiguration.class})
public abstract class BaseTest {


}
