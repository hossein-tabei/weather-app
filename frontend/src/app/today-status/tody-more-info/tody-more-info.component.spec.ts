import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TodyMoreInfoComponent } from './tody-more-info.component';

describe('TodyMoreInfoComponent', () => {
  let component: TodyMoreInfoComponent;
  let fixture: ComponentFixture<TodyMoreInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodyMoreInfoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TodyMoreInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
