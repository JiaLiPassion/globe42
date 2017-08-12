import { TestBed } from '@angular/core/testing';

import { IncomeSourcesResolverService } from './income-sources-resolver.service';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { IncomeSourceModel } from './models/income-source.model';
import { IncomeSourceService } from './income-source.service';

describe('IncomeSourcesResolverService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [IncomeSourcesResolverService, IncomeSourceService],
    imports: [HttpClientModule]
  }));

  it('should retrieve sources', () => {
    const incomeSourceService = TestBed.get(IncomeSourceService);
    const expectedResults = Observable.of([{ id: 42, name: 'Allocations Familiales' }] as Array<IncomeSourceModel>);

    spyOn(incomeSourceService, 'list').and.returnValue(expectedResults);

    const resolver = TestBed.get(IncomeSourcesResolverService);
    const result = resolver.resolve();

    expect(result).toBe(expectedResults);
    expect(incomeSourceService.list).toHaveBeenCalled();
  });
});
