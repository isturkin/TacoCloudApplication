package com.ivanturkin.cloud.app.taco.configuration.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Component
public class OrderProperties {

  @Min(value = 5, message = "must be between 5 and 25")
  @Max(value = 25, message = "must be between 5 and 25")
  private int pageSize = 20;
}
