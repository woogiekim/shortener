package com.shorten.component;

import com.shorten.domain.entity.RedirectUrl;
import com.shorten.domain.entity.ShortenUrl;

public interface ShortenUrlGenerator {

  ShortenUrl generate(final RedirectUrl redirectUrl);
}
