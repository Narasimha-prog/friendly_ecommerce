package com.lnreddy.friendlyecommerce.user.domain.model.valueobject;

import com.lnreddy.friendlyecommerce.user.domain.exception.RoleNotFound;

public record Role(UserRoleStatus roleStatus) {
    public Role {
      if(roleStatus == null)
          throw new RoleNotFound("This Role is Found: ");
    }
}
