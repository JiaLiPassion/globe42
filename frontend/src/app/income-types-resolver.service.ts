import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';

import { IncomeSourceTypeModel } from './models/income-source-type.model';
import { IncomeSourceTypeService } from './income-source-type.service';

@Injectable()
export class IncomeTypesResolverService implements Resolve<Array<IncomeSourceTypeModel>> {

  constructor(private incomeService: IncomeSourceTypeService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Array<IncomeSourceTypeModel>> {
    return this.incomeService.list();
  }

}
